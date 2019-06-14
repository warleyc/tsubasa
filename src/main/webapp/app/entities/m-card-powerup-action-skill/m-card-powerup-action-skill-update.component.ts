import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMCardPowerupActionSkill, MCardPowerupActionSkill } from 'app/shared/model/m-card-powerup-action-skill.model';
import { MCardPowerupActionSkillService } from './m-card-powerup-action-skill.service';
import { IMCardThumbnailAssets } from 'app/shared/model/m-card-thumbnail-assets.model';
import { MCardThumbnailAssetsService } from 'app/entities/m-card-thumbnail-assets';

@Component({
  selector: 'jhi-m-card-powerup-action-skill-update',
  templateUrl: './m-card-powerup-action-skill-update.component.html'
})
export class MCardPowerupActionSkillUpdateComponent implements OnInit {
  mCardPowerupActionSkill: IMCardPowerupActionSkill;
  isSaving: boolean;

  mcardthumbnailassets: IMCardThumbnailAssets[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    shortName: [null, [Validators.required]],
    description: [null, [Validators.required]],
    rarity: [null, [Validators.required]],
    attribute: [null, [Validators.required]],
    actionRarity: [null, [Validators.required]],
    gainActionExp: [null, [Validators.required]],
    coin: [null, [Validators.required]],
    sellMedalId: [],
    thumbnailAssetsId: [null, [Validators.required]],
    cardIllustAssetsId: [null, [Validators.required]],
    targetActionCommandType: [],
    targetCharacterId: [],
    mcardthumbnailassetsId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mCardPowerupActionSkillService: MCardPowerupActionSkillService,
    protected mCardThumbnailAssetsService: MCardThumbnailAssetsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mCardPowerupActionSkill }) => {
      this.updateForm(mCardPowerupActionSkill);
      this.mCardPowerupActionSkill = mCardPowerupActionSkill;
    });
    this.mCardThumbnailAssetsService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMCardThumbnailAssets[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMCardThumbnailAssets[]>) => response.body)
      )
      .subscribe(
        (res: IMCardThumbnailAssets[]) => (this.mcardthumbnailassets = res),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(mCardPowerupActionSkill: IMCardPowerupActionSkill) {
    this.editForm.patchValue({
      id: mCardPowerupActionSkill.id,
      name: mCardPowerupActionSkill.name,
      shortName: mCardPowerupActionSkill.shortName,
      description: mCardPowerupActionSkill.description,
      rarity: mCardPowerupActionSkill.rarity,
      attribute: mCardPowerupActionSkill.attribute,
      actionRarity: mCardPowerupActionSkill.actionRarity,
      gainActionExp: mCardPowerupActionSkill.gainActionExp,
      coin: mCardPowerupActionSkill.coin,
      sellMedalId: mCardPowerupActionSkill.sellMedalId,
      thumbnailAssetsId: mCardPowerupActionSkill.thumbnailAssetsId,
      cardIllustAssetsId: mCardPowerupActionSkill.cardIllustAssetsId,
      targetActionCommandType: mCardPowerupActionSkill.targetActionCommandType,
      targetCharacterId: mCardPowerupActionSkill.targetCharacterId,
      mcardthumbnailassetsId: mCardPowerupActionSkill.mcardthumbnailassetsId
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
    const mCardPowerupActionSkill = this.createFromForm();
    if (mCardPowerupActionSkill.id !== undefined) {
      this.subscribeToSaveResponse(this.mCardPowerupActionSkillService.update(mCardPowerupActionSkill));
    } else {
      this.subscribeToSaveResponse(this.mCardPowerupActionSkillService.create(mCardPowerupActionSkill));
    }
  }

  private createFromForm(): IMCardPowerupActionSkill {
    const entity = {
      ...new MCardPowerupActionSkill(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      shortName: this.editForm.get(['shortName']).value,
      description: this.editForm.get(['description']).value,
      rarity: this.editForm.get(['rarity']).value,
      attribute: this.editForm.get(['attribute']).value,
      actionRarity: this.editForm.get(['actionRarity']).value,
      gainActionExp: this.editForm.get(['gainActionExp']).value,
      coin: this.editForm.get(['coin']).value,
      sellMedalId: this.editForm.get(['sellMedalId']).value,
      thumbnailAssetsId: this.editForm.get(['thumbnailAssetsId']).value,
      cardIllustAssetsId: this.editForm.get(['cardIllustAssetsId']).value,
      targetActionCommandType: this.editForm.get(['targetActionCommandType']).value,
      targetCharacterId: this.editForm.get(['targetCharacterId']).value,
      mcardthumbnailassetsId: this.editForm.get(['mcardthumbnailassetsId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMCardPowerupActionSkill>>) {
    result.subscribe((res: HttpResponse<IMCardPowerupActionSkill>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMCardThumbnailAssetsById(index: number, item: IMCardThumbnailAssets) {
    return item.id;
  }
}
