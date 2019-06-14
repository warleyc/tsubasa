import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMMission, MMission } from 'app/shared/model/m-mission.model';
import { MMissionService } from './m-mission.service';
import { IMMissionReward } from 'app/shared/model/m-mission-reward.model';
import { MMissionRewardService } from 'app/entities/m-mission-reward';

@Component({
  selector: 'jhi-m-mission-update',
  templateUrl: './m-mission-update.component.html'
})
export class MMissionUpdateComponent implements OnInit {
  mMission: IMMission;
  isSaving: boolean;

  mmissionrewards: IMMissionReward[];

  editForm = this.fb.group({
    id: [],
    term: [null, [Validators.required]],
    roundNum: [],
    title: [],
    description: [],
    missionType: [null, [Validators.required]],
    absolute: [null, [Validators.required]],
    param1: [],
    param2: [],
    param3: [],
    param4: [],
    param5: [],
    trigger: [null, [Validators.required]],
    triggerCondition: [null, [Validators.required]],
    rewardId: [null, [Validators.required]],
    startAt: [null, [Validators.required]],
    endAt: [],
    link: [],
    sceneTransitionParam: [],
    pickup: [null, [Validators.required]],
    orderNum: [null, [Validators.required]],
    idId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mMissionService: MMissionService,
    protected mMissionRewardService: MMissionRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMission }) => {
      this.updateForm(mMission);
      this.mMission = mMission;
    });
    this.mMissionRewardService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMMissionReward[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMMissionReward[]>) => response.body)
      )
      .subscribe((res: IMMissionReward[]) => (this.mmissionrewards = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mMission: IMMission) {
    this.editForm.patchValue({
      id: mMission.id,
      term: mMission.term,
      roundNum: mMission.roundNum,
      title: mMission.title,
      description: mMission.description,
      missionType: mMission.missionType,
      absolute: mMission.absolute,
      param1: mMission.param1,
      param2: mMission.param2,
      param3: mMission.param3,
      param4: mMission.param4,
      param5: mMission.param5,
      trigger: mMission.trigger,
      triggerCondition: mMission.triggerCondition,
      rewardId: mMission.rewardId,
      startAt: mMission.startAt,
      endAt: mMission.endAt,
      link: mMission.link,
      sceneTransitionParam: mMission.sceneTransitionParam,
      pickup: mMission.pickup,
      orderNum: mMission.orderNum,
      idId: mMission.idId
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
    const mMission = this.createFromForm();
    if (mMission.id !== undefined) {
      this.subscribeToSaveResponse(this.mMissionService.update(mMission));
    } else {
      this.subscribeToSaveResponse(this.mMissionService.create(mMission));
    }
  }

  private createFromForm(): IMMission {
    const entity = {
      ...new MMission(),
      id: this.editForm.get(['id']).value,
      term: this.editForm.get(['term']).value,
      roundNum: this.editForm.get(['roundNum']).value,
      title: this.editForm.get(['title']).value,
      description: this.editForm.get(['description']).value,
      missionType: this.editForm.get(['missionType']).value,
      absolute: this.editForm.get(['absolute']).value,
      param1: this.editForm.get(['param1']).value,
      param2: this.editForm.get(['param2']).value,
      param3: this.editForm.get(['param3']).value,
      param4: this.editForm.get(['param4']).value,
      param5: this.editForm.get(['param5']).value,
      trigger: this.editForm.get(['trigger']).value,
      triggerCondition: this.editForm.get(['triggerCondition']).value,
      rewardId: this.editForm.get(['rewardId']).value,
      startAt: this.editForm.get(['startAt']).value,
      endAt: this.editForm.get(['endAt']).value,
      link: this.editForm.get(['link']).value,
      sceneTransitionParam: this.editForm.get(['sceneTransitionParam']).value,
      pickup: this.editForm.get(['pickup']).value,
      orderNum: this.editForm.get(['orderNum']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMission>>) {
    result.subscribe((res: HttpResponse<IMMission>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
