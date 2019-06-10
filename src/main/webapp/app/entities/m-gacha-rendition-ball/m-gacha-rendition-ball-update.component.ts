import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMGachaRenditionBall, MGachaRenditionBall } from 'app/shared/model/m-gacha-rendition-ball.model';
import { MGachaRenditionBallService } from './m-gacha-rendition-ball.service';

@Component({
  selector: 'jhi-m-gacha-rendition-ball-update',
  templateUrl: './m-gacha-rendition-ball-update.component.html'
})
export class MGachaRenditionBallUpdateComponent implements OnInit {
  mGachaRenditionBall: IMGachaRenditionBall;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    renditionId: [null, [Validators.required]],
    isSsr: [null, [Validators.required]],
    weight: [null, [Validators.required]],
    ballTextureName: [null, [Validators.required]],
    trajectoryNormalTextureName: [null, [Validators.required]],
    trajectoryGoldTextureName: [null, [Validators.required]],
    trajectoryRainbowTextureName: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mGachaRenditionBallService: MGachaRenditionBallService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGachaRenditionBall }) => {
      this.updateForm(mGachaRenditionBall);
      this.mGachaRenditionBall = mGachaRenditionBall;
    });
  }

  updateForm(mGachaRenditionBall: IMGachaRenditionBall) {
    this.editForm.patchValue({
      id: mGachaRenditionBall.id,
      renditionId: mGachaRenditionBall.renditionId,
      isSsr: mGachaRenditionBall.isSsr,
      weight: mGachaRenditionBall.weight,
      ballTextureName: mGachaRenditionBall.ballTextureName,
      trajectoryNormalTextureName: mGachaRenditionBall.trajectoryNormalTextureName,
      trajectoryGoldTextureName: mGachaRenditionBall.trajectoryGoldTextureName,
      trajectoryRainbowTextureName: mGachaRenditionBall.trajectoryRainbowTextureName
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
    const mGachaRenditionBall = this.createFromForm();
    if (mGachaRenditionBall.id !== undefined) {
      this.subscribeToSaveResponse(this.mGachaRenditionBallService.update(mGachaRenditionBall));
    } else {
      this.subscribeToSaveResponse(this.mGachaRenditionBallService.create(mGachaRenditionBall));
    }
  }

  private createFromForm(): IMGachaRenditionBall {
    const entity = {
      ...new MGachaRenditionBall(),
      id: this.editForm.get(['id']).value,
      renditionId: this.editForm.get(['renditionId']).value,
      isSsr: this.editForm.get(['isSsr']).value,
      weight: this.editForm.get(['weight']).value,
      ballTextureName: this.editForm.get(['ballTextureName']).value,
      trajectoryNormalTextureName: this.editForm.get(['trajectoryNormalTextureName']).value,
      trajectoryGoldTextureName: this.editForm.get(['trajectoryGoldTextureName']).value,
      trajectoryRainbowTextureName: this.editForm.get(['trajectoryRainbowTextureName']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGachaRenditionBall>>) {
    result.subscribe((res: HttpResponse<IMGachaRenditionBall>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
