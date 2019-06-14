import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import {
  IMGachaRenditionTrajectoryCutIn,
  MGachaRenditionTrajectoryCutIn
} from 'app/shared/model/m-gacha-rendition-trajectory-cut-in.model';
import { MGachaRenditionTrajectoryCutInService } from './m-gacha-rendition-trajectory-cut-in.service';

@Component({
  selector: 'jhi-m-gacha-rendition-trajectory-cut-in-update',
  templateUrl: './m-gacha-rendition-trajectory-cut-in-update.component.html'
})
export class MGachaRenditionTrajectoryCutInUpdateComponent implements OnInit {
  mGachaRenditionTrajectoryCutIn: IMGachaRenditionTrajectoryCutIn;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    renditionId: [null, [Validators.required]],
    trajectoryType: [null, [Validators.required]],
    spriteName: [null, [Validators.required]],
    weight: [null, [Validators.required]],
    voice: [null, [Validators.required]],
    exceptKickerId: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mGachaRenditionTrajectoryCutInService: MGachaRenditionTrajectoryCutInService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGachaRenditionTrajectoryCutIn }) => {
      this.updateForm(mGachaRenditionTrajectoryCutIn);
      this.mGachaRenditionTrajectoryCutIn = mGachaRenditionTrajectoryCutIn;
    });
  }

  updateForm(mGachaRenditionTrajectoryCutIn: IMGachaRenditionTrajectoryCutIn) {
    this.editForm.patchValue({
      id: mGachaRenditionTrajectoryCutIn.id,
      renditionId: mGachaRenditionTrajectoryCutIn.renditionId,
      trajectoryType: mGachaRenditionTrajectoryCutIn.trajectoryType,
      spriteName: mGachaRenditionTrajectoryCutIn.spriteName,
      weight: mGachaRenditionTrajectoryCutIn.weight,
      voice: mGachaRenditionTrajectoryCutIn.voice,
      exceptKickerId: mGachaRenditionTrajectoryCutIn.exceptKickerId
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
    const mGachaRenditionTrajectoryCutIn = this.createFromForm();
    if (mGachaRenditionTrajectoryCutIn.id !== undefined) {
      this.subscribeToSaveResponse(this.mGachaRenditionTrajectoryCutInService.update(mGachaRenditionTrajectoryCutIn));
    } else {
      this.subscribeToSaveResponse(this.mGachaRenditionTrajectoryCutInService.create(mGachaRenditionTrajectoryCutIn));
    }
  }

  private createFromForm(): IMGachaRenditionTrajectoryCutIn {
    const entity = {
      ...new MGachaRenditionTrajectoryCutIn(),
      id: this.editForm.get(['id']).value,
      renditionId: this.editForm.get(['renditionId']).value,
      trajectoryType: this.editForm.get(['trajectoryType']).value,
      spriteName: this.editForm.get(['spriteName']).value,
      weight: this.editForm.get(['weight']).value,
      voice: this.editForm.get(['voice']).value,
      exceptKickerId: this.editForm.get(['exceptKickerId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGachaRenditionTrajectoryCutIn>>) {
    result.subscribe(
      (res: HttpResponse<IMGachaRenditionTrajectoryCutIn>) => this.onSaveSuccess(),
      (res: HttpErrorResponse) => this.onSaveError()
    );
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
