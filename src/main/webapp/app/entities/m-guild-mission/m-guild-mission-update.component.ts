import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMGuildMission, MGuildMission } from 'app/shared/model/m-guild-mission.model';
import { MGuildMissionService } from './m-guild-mission.service';
import { IMMissionReward } from 'app/shared/model/m-mission-reward.model';
import { MMissionRewardService } from 'app/entities/m-mission-reward';

@Component({
  selector: 'jhi-m-guild-mission-update',
  templateUrl: './m-guild-mission-update.component.html'
})
export class MGuildMissionUpdateComponent implements OnInit {
  mGuildMission: IMGuildMission;
  isSaving: boolean;

  mmissionrewards: IMMissionReward[];

  editForm = this.fb.group({
    id: [],
    term: [null, [Validators.required]],
    title: [null, [Validators.required]],
    description: [null, [Validators.required]],
    missionType: [null, [Validators.required]],
    param1: [],
    rewardId: [null, [Validators.required]],
    link: [],
    sceneTransitionParam: [],
    pickup: [null, [Validators.required]],
    triggerMissionId: [],
    orderNum: [null, [Validators.required]],
    idId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mGuildMissionService: MGuildMissionService,
    protected mMissionRewardService: MMissionRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGuildMission }) => {
      this.updateForm(mGuildMission);
      this.mGuildMission = mGuildMission;
    });
    this.mMissionRewardService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMMissionReward[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMMissionReward[]>) => response.body)
      )
      .subscribe((res: IMMissionReward[]) => (this.mmissionrewards = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mGuildMission: IMGuildMission) {
    this.editForm.patchValue({
      id: mGuildMission.id,
      term: mGuildMission.term,
      title: mGuildMission.title,
      description: mGuildMission.description,
      missionType: mGuildMission.missionType,
      param1: mGuildMission.param1,
      rewardId: mGuildMission.rewardId,
      link: mGuildMission.link,
      sceneTransitionParam: mGuildMission.sceneTransitionParam,
      pickup: mGuildMission.pickup,
      triggerMissionId: mGuildMission.triggerMissionId,
      orderNum: mGuildMission.orderNum,
      idId: mGuildMission.idId
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
    const mGuildMission = this.createFromForm();
    if (mGuildMission.id !== undefined) {
      this.subscribeToSaveResponse(this.mGuildMissionService.update(mGuildMission));
    } else {
      this.subscribeToSaveResponse(this.mGuildMissionService.create(mGuildMission));
    }
  }

  private createFromForm(): IMGuildMission {
    const entity = {
      ...new MGuildMission(),
      id: this.editForm.get(['id']).value,
      term: this.editForm.get(['term']).value,
      title: this.editForm.get(['title']).value,
      description: this.editForm.get(['description']).value,
      missionType: this.editForm.get(['missionType']).value,
      param1: this.editForm.get(['param1']).value,
      rewardId: this.editForm.get(['rewardId']).value,
      link: this.editForm.get(['link']).value,
      sceneTransitionParam: this.editForm.get(['sceneTransitionParam']).value,
      pickup: this.editForm.get(['pickup']).value,
      triggerMissionId: this.editForm.get(['triggerMissionId']).value,
      orderNum: this.editForm.get(['orderNum']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGuildMission>>) {
    result.subscribe((res: HttpResponse<IMGuildMission>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMMissionRewardById(index: number, item: IMMissionReward) {
    return item.id;
  }
}
