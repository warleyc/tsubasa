import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMCheatCaution, MCheatCaution } from 'app/shared/model/m-cheat-caution.model';
import { MCheatCautionService } from './m-cheat-caution.service';

@Component({
  selector: 'jhi-m-cheat-caution-update',
  templateUrl: './m-cheat-caution-update.component.html'
})
export class MCheatCautionUpdateComponent implements OnInit {
  mCheatCaution: IMCheatCaution;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    caution: [null, [Validators.required]],
    description: [null, [Validators.required]],
    imageAssetName: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mCheatCautionService: MCheatCautionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mCheatCaution }) => {
      this.updateForm(mCheatCaution);
      this.mCheatCaution = mCheatCaution;
    });
  }

  updateForm(mCheatCaution: IMCheatCaution) {
    this.editForm.patchValue({
      id: mCheatCaution.id,
      caution: mCheatCaution.caution,
      description: mCheatCaution.description,
      imageAssetName: mCheatCaution.imageAssetName
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
    const mCheatCaution = this.createFromForm();
    if (mCheatCaution.id !== undefined) {
      this.subscribeToSaveResponse(this.mCheatCautionService.update(mCheatCaution));
    } else {
      this.subscribeToSaveResponse(this.mCheatCautionService.create(mCheatCaution));
    }
  }

  private createFromForm(): IMCheatCaution {
    const entity = {
      ...new MCheatCaution(),
      id: this.editForm.get(['id']).value,
      caution: this.editForm.get(['caution']).value,
      description: this.editForm.get(['description']).value,
      imageAssetName: this.editForm.get(['imageAssetName']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMCheatCaution>>) {
    result.subscribe((res: HttpResponse<IMCheatCaution>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
