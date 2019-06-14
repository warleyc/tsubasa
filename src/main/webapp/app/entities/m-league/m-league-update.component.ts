import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMLeague, MLeague } from 'app/shared/model/m-league.model';
import { MLeagueService } from './m-league.service';

@Component({
  selector: 'jhi-m-league-update',
  templateUrl: './m-league-update.component.html'
})
export class MLeagueUpdateComponent implements OnInit {
  mLeague: IMLeague;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    hierarchy: [null, [Validators.required]],
    weight: [null, [Validators.required]],
    name: [null, [Validators.required]],
    rooms: [null, [Validators.required]],
    promotionRate: [null, [Validators.required]],
    demotionRate: [null, [Validators.required]],
    totalParameterRangeUpper: [null, [Validators.required]],
    totalParameterRangeLower: [null, [Validators.required]],
    requiredMatches: [],
    startWeekId: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mLeagueService: MLeagueService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mLeague }) => {
      this.updateForm(mLeague);
      this.mLeague = mLeague;
    });
  }

  updateForm(mLeague: IMLeague) {
    this.editForm.patchValue({
      id: mLeague.id,
      hierarchy: mLeague.hierarchy,
      weight: mLeague.weight,
      name: mLeague.name,
      rooms: mLeague.rooms,
      promotionRate: mLeague.promotionRate,
      demotionRate: mLeague.demotionRate,
      totalParameterRangeUpper: mLeague.totalParameterRangeUpper,
      totalParameterRangeLower: mLeague.totalParameterRangeLower,
      requiredMatches: mLeague.requiredMatches,
      startWeekId: mLeague.startWeekId
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
    const mLeague = this.createFromForm();
    if (mLeague.id !== undefined) {
      this.subscribeToSaveResponse(this.mLeagueService.update(mLeague));
    } else {
      this.subscribeToSaveResponse(this.mLeagueService.create(mLeague));
    }
  }

  private createFromForm(): IMLeague {
    const entity = {
      ...new MLeague(),
      id: this.editForm.get(['id']).value,
      hierarchy: this.editForm.get(['hierarchy']).value,
      weight: this.editForm.get(['weight']).value,
      name: this.editForm.get(['name']).value,
      rooms: this.editForm.get(['rooms']).value,
      promotionRate: this.editForm.get(['promotionRate']).value,
      demotionRate: this.editForm.get(['demotionRate']).value,
      totalParameterRangeUpper: this.editForm.get(['totalParameterRangeUpper']).value,
      totalParameterRangeLower: this.editForm.get(['totalParameterRangeLower']).value,
      requiredMatches: this.editForm.get(['requiredMatches']).value,
      startWeekId: this.editForm.get(['startWeekId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMLeague>>) {
    result.subscribe((res: HttpResponse<IMLeague>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
