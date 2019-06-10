import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMAnnounceText, MAnnounceText } from 'app/shared/model/m-announce-text.model';
import { MAnnounceTextService } from './m-announce-text.service';

@Component({
  selector: 'jhi-m-announce-text-update',
  templateUrl: './m-announce-text-update.component.html'
})
export class MAnnounceTextUpdateComponent implements OnInit {
  mAnnounceText: IMAnnounceText;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    seqNo: [null, [Validators.required]],
    normalAnnounce: [null, [Validators.required]],
    criticalSAnnounce: [null, [Validators.required]],
    criticalMAnnounce: [null, [Validators.required]],
    criticalLAnnounce: [null, [Validators.required]],
    delayTime: [],
    continueTime: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mAnnounceTextService: MAnnounceTextService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mAnnounceText }) => {
      this.updateForm(mAnnounceText);
      this.mAnnounceText = mAnnounceText;
    });
  }

  updateForm(mAnnounceText: IMAnnounceText) {
    this.editForm.patchValue({
      id: mAnnounceText.id,
      groupId: mAnnounceText.groupId,
      seqNo: mAnnounceText.seqNo,
      normalAnnounce: mAnnounceText.normalAnnounce,
      criticalSAnnounce: mAnnounceText.criticalSAnnounce,
      criticalMAnnounce: mAnnounceText.criticalMAnnounce,
      criticalLAnnounce: mAnnounceText.criticalLAnnounce,
      delayTime: mAnnounceText.delayTime,
      continueTime: mAnnounceText.continueTime
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
    const mAnnounceText = this.createFromForm();
    if (mAnnounceText.id !== undefined) {
      this.subscribeToSaveResponse(this.mAnnounceTextService.update(mAnnounceText));
    } else {
      this.subscribeToSaveResponse(this.mAnnounceTextService.create(mAnnounceText));
    }
  }

  private createFromForm(): IMAnnounceText {
    const entity = {
      ...new MAnnounceText(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      seqNo: this.editForm.get(['seqNo']).value,
      normalAnnounce: this.editForm.get(['normalAnnounce']).value,
      criticalSAnnounce: this.editForm.get(['criticalSAnnounce']).value,
      criticalMAnnounce: this.editForm.get(['criticalMAnnounce']).value,
      criticalLAnnounce: this.editForm.get(['criticalLAnnounce']).value,
      delayTime: this.editForm.get(['delayTime']).value,
      continueTime: this.editForm.get(['continueTime']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMAnnounceText>>) {
    result.subscribe((res: HttpResponse<IMAnnounceText>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
