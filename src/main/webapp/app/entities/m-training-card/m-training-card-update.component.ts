import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMTrainingCard, MTrainingCard } from 'app/shared/model/m-training-card.model';
import { MTrainingCardService } from './m-training-card.service';
import { IMCardThumbnailAssets } from 'app/shared/model/m-card-thumbnail-assets.model';
import { MCardThumbnailAssetsService } from 'app/entities/m-card-thumbnail-assets';

@Component({
  selector: 'jhi-m-training-card-update',
  templateUrl: './m-training-card-update.component.html'
})
export class MTrainingCardUpdateComponent implements OnInit {
  mTrainingCard: IMTrainingCard;
  isSaving: boolean;

  mcardthumbnailassets: IMCardThumbnailAssets[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    shortName: [null, [Validators.required]],
    description: [null, [Validators.required]],
    rarity: [null, [Validators.required]],
    attribute: [null, [Validators.required]],
    gainExp: [null, [Validators.required]],
    coin: [null, [Validators.required]],
    sellMedalId: [],
    plusDribble: [null, [Validators.required]],
    plusShoot: [null, [Validators.required]],
    plusPass: [null, [Validators.required]],
    plusTackle: [null, [Validators.required]],
    plusBlock: [null, [Validators.required]],
    plusIntercept: [null, [Validators.required]],
    plusSpeed: [null, [Validators.required]],
    plusPower: [null, [Validators.required]],
    plusTechnique: [null, [Validators.required]],
    plusPunching: [null, [Validators.required]],
    plusCatching: [null, [Validators.required]],
    thumbnailAssetsId: [null, [Validators.required]],
    cardIllustAssetsId: [null, [Validators.required]],
    mcardthumbnailassetsId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mTrainingCardService: MTrainingCardService,
    protected mCardThumbnailAssetsService: MCardThumbnailAssetsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTrainingCard }) => {
      this.updateForm(mTrainingCard);
      this.mTrainingCard = mTrainingCard;
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

  updateForm(mTrainingCard: IMTrainingCard) {
    this.editForm.patchValue({
      id: mTrainingCard.id,
      name: mTrainingCard.name,
      shortName: mTrainingCard.shortName,
      description: mTrainingCard.description,
      rarity: mTrainingCard.rarity,
      attribute: mTrainingCard.attribute,
      gainExp: mTrainingCard.gainExp,
      coin: mTrainingCard.coin,
      sellMedalId: mTrainingCard.sellMedalId,
      plusDribble: mTrainingCard.plusDribble,
      plusShoot: mTrainingCard.plusShoot,
      plusPass: mTrainingCard.plusPass,
      plusTackle: mTrainingCard.plusTackle,
      plusBlock: mTrainingCard.plusBlock,
      plusIntercept: mTrainingCard.plusIntercept,
      plusSpeed: mTrainingCard.plusSpeed,
      plusPower: mTrainingCard.plusPower,
      plusTechnique: mTrainingCard.plusTechnique,
      plusPunching: mTrainingCard.plusPunching,
      plusCatching: mTrainingCard.plusCatching,
      thumbnailAssetsId: mTrainingCard.thumbnailAssetsId,
      cardIllustAssetsId: mTrainingCard.cardIllustAssetsId,
      mcardthumbnailassetsId: mTrainingCard.mcardthumbnailassetsId
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
    const mTrainingCard = this.createFromForm();
    if (mTrainingCard.id !== undefined) {
      this.subscribeToSaveResponse(this.mTrainingCardService.update(mTrainingCard));
    } else {
      this.subscribeToSaveResponse(this.mTrainingCardService.create(mTrainingCard));
    }
  }

  private createFromForm(): IMTrainingCard {
    const entity = {
      ...new MTrainingCard(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      shortName: this.editForm.get(['shortName']).value,
      description: this.editForm.get(['description']).value,
      rarity: this.editForm.get(['rarity']).value,
      attribute: this.editForm.get(['attribute']).value,
      gainExp: this.editForm.get(['gainExp']).value,
      coin: this.editForm.get(['coin']).value,
      sellMedalId: this.editForm.get(['sellMedalId']).value,
      plusDribble: this.editForm.get(['plusDribble']).value,
      plusShoot: this.editForm.get(['plusShoot']).value,
      plusPass: this.editForm.get(['plusPass']).value,
      plusTackle: this.editForm.get(['plusTackle']).value,
      plusBlock: this.editForm.get(['plusBlock']).value,
      plusIntercept: this.editForm.get(['plusIntercept']).value,
      plusSpeed: this.editForm.get(['plusSpeed']).value,
      plusPower: this.editForm.get(['plusPower']).value,
      plusTechnique: this.editForm.get(['plusTechnique']).value,
      plusPunching: this.editForm.get(['plusPunching']).value,
      plusCatching: this.editForm.get(['plusCatching']).value,
      thumbnailAssetsId: this.editForm.get(['thumbnailAssetsId']).value,
      cardIllustAssetsId: this.editForm.get(['cardIllustAssetsId']).value,
      mcardthumbnailassetsId: this.editForm.get(['mcardthumbnailassetsId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTrainingCard>>) {
    result.subscribe((res: HttpResponse<IMTrainingCard>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
