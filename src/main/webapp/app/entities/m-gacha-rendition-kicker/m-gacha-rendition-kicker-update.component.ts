import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMGachaRenditionKicker, MGachaRenditionKicker } from 'app/shared/model/m-gacha-rendition-kicker.model';
import { MGachaRenditionKickerService } from './m-gacha-rendition-kicker.service';

@Component({
  selector: 'jhi-m-gacha-rendition-kicker-update',
  templateUrl: './m-gacha-rendition-kicker-update.component.html'
})
export class MGachaRenditionKickerUpdateComponent implements OnInit {
  mGachaRenditionKicker: IMGachaRenditionKicker;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    renditionId: [null, [Validators.required]],
    isManySsr: [null, [Validators.required]],
    isSsr: [null, [Validators.required]],
    weight: [null, [Validators.required]],
    leftModelId: [null, [Validators.required]],
    leftUniformUpId: [null, [Validators.required]],
    leftUniformBottomId: [null, [Validators.required]],
    leftUniformNumber: [null, [Validators.required]],
    rightModelId: [null, [Validators.required]],
    rightUniformUpId: [null, [Validators.required]],
    rightUniformBottomId: [null, [Validators.required]],
    rightUniformNumber: [null, [Validators.required]],
    cutInSpriteName: [null, [Validators.required]],
    leftMessage: [null, [Validators.required]],
    rightMessage: [null, [Validators.required]],
    voiceTrigger: [null, [Validators.required]],
    voiceStartCutIn: [null, [Validators.required]],
    kickerId: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mGachaRenditionKickerService: MGachaRenditionKickerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGachaRenditionKicker }) => {
      this.updateForm(mGachaRenditionKicker);
      this.mGachaRenditionKicker = mGachaRenditionKicker;
    });
  }

  updateForm(mGachaRenditionKicker: IMGachaRenditionKicker) {
    this.editForm.patchValue({
      id: mGachaRenditionKicker.id,
      renditionId: mGachaRenditionKicker.renditionId,
      isManySsr: mGachaRenditionKicker.isManySsr,
      isSsr: mGachaRenditionKicker.isSsr,
      weight: mGachaRenditionKicker.weight,
      leftModelId: mGachaRenditionKicker.leftModelId,
      leftUniformUpId: mGachaRenditionKicker.leftUniformUpId,
      leftUniformBottomId: mGachaRenditionKicker.leftUniformBottomId,
      leftUniformNumber: mGachaRenditionKicker.leftUniformNumber,
      rightModelId: mGachaRenditionKicker.rightModelId,
      rightUniformUpId: mGachaRenditionKicker.rightUniformUpId,
      rightUniformBottomId: mGachaRenditionKicker.rightUniformBottomId,
      rightUniformNumber: mGachaRenditionKicker.rightUniformNumber,
      cutInSpriteName: mGachaRenditionKicker.cutInSpriteName,
      leftMessage: mGachaRenditionKicker.leftMessage,
      rightMessage: mGachaRenditionKicker.rightMessage,
      voiceTrigger: mGachaRenditionKicker.voiceTrigger,
      voiceStartCutIn: mGachaRenditionKicker.voiceStartCutIn,
      kickerId: mGachaRenditionKicker.kickerId
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
    const mGachaRenditionKicker = this.createFromForm();
    if (mGachaRenditionKicker.id !== undefined) {
      this.subscribeToSaveResponse(this.mGachaRenditionKickerService.update(mGachaRenditionKicker));
    } else {
      this.subscribeToSaveResponse(this.mGachaRenditionKickerService.create(mGachaRenditionKicker));
    }
  }

  private createFromForm(): IMGachaRenditionKicker {
    const entity = {
      ...new MGachaRenditionKicker(),
      id: this.editForm.get(['id']).value,
      renditionId: this.editForm.get(['renditionId']).value,
      isManySsr: this.editForm.get(['isManySsr']).value,
      isSsr: this.editForm.get(['isSsr']).value,
      weight: this.editForm.get(['weight']).value,
      leftModelId: this.editForm.get(['leftModelId']).value,
      leftUniformUpId: this.editForm.get(['leftUniformUpId']).value,
      leftUniformBottomId: this.editForm.get(['leftUniformBottomId']).value,
      leftUniformNumber: this.editForm.get(['leftUniformNumber']).value,
      rightModelId: this.editForm.get(['rightModelId']).value,
      rightUniformUpId: this.editForm.get(['rightUniformUpId']).value,
      rightUniformBottomId: this.editForm.get(['rightUniformBottomId']).value,
      rightUniformNumber: this.editForm.get(['rightUniformNumber']).value,
      cutInSpriteName: this.editForm.get(['cutInSpriteName']).value,
      leftMessage: this.editForm.get(['leftMessage']).value,
      rightMessage: this.editForm.get(['rightMessage']).value,
      voiceTrigger: this.editForm.get(['voiceTrigger']).value,
      voiceStartCutIn: this.editForm.get(['voiceStartCutIn']).value,
      kickerId: this.editForm.get(['kickerId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGachaRenditionKicker>>) {
    result.subscribe((res: HttpResponse<IMGachaRenditionKicker>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
