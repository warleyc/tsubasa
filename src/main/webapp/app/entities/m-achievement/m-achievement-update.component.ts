import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMAchievement, MAchievement } from 'app/shared/model/m-achievement.model';
import { MAchievementService } from './m-achievement.service';
import { IMMission } from 'app/shared/model/m-mission.model';
import { MMissionService } from 'app/entities/m-mission';

@Component({
  selector: 'jhi-m-achievement-update',
  templateUrl: './m-achievement-update.component.html'
})
export class MAchievementUpdateComponent implements OnInit {
  mAchievement: IMAchievement;
  isSaving: boolean;

  mmissions: IMMission[];

  editForm = this.fb.group({
    id: [],
    achievementId: [null, [Validators.required]],
    name: [null, [Validators.required]],
    type: [null, [Validators.required]],
    missionId: [null, [Validators.required]],
    idId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mAchievementService: MAchievementService,
    protected mMissionService: MMissionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mAchievement }) => {
      this.updateForm(mAchievement);
      this.mAchievement = mAchievement;
    });
    this.mMissionService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMMission[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMMission[]>) => response.body)
      )
      .subscribe((res: IMMission[]) => (this.mmissions = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mAchievement: IMAchievement) {
    this.editForm.patchValue({
      id: mAchievement.id,
      achievementId: mAchievement.achievementId,
      name: mAchievement.name,
      type: mAchievement.type,
      missionId: mAchievement.missionId,
      idId: mAchievement.idId
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file = event.target.files[0];
        if (isImage && !/^image\//.test(file.type)) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      () => console.log('blob added'), // sucess
      this.onError
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mAchievement = this.createFromForm();
    if (mAchievement.id !== undefined) {
      this.subscribeToSaveResponse(this.mAchievementService.update(mAchievement));
    } else {
      this.subscribeToSaveResponse(this.mAchievementService.create(mAchievement));
    }
  }

  private createFromForm(): IMAchievement {
    const entity = {
      ...new MAchievement(),
      id: this.editForm.get(['id']).value,
      achievementId: this.editForm.get(['achievementId']).value,
      name: this.editForm.get(['name']).value,
      type: this.editForm.get(['type']).value,
      missionId: this.editForm.get(['missionId']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMAchievement>>) {
    result.subscribe((res: HttpResponse<IMAchievement>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackMMissionById(index: number, item: IMMission) {
    return item.id;
  }
}
