import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMBadge, MBadge } from 'app/shared/model/m-badge.model';
import { MBadgeService } from './m-badge.service';

@Component({
  selector: 'jhi-m-badge-update',
  templateUrl: './m-badge-update.component.html'
})
export class MBadgeUpdateComponent implements OnInit {
  mBadge: IMBadge;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    type: [null, [Validators.required]],
    description: [null, [Validators.required]],
    assetName: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mBadgeService: MBadgeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mBadge }) => {
      this.updateForm(mBadge);
      this.mBadge = mBadge;
    });
  }

  updateForm(mBadge: IMBadge) {
    this.editForm.patchValue({
      id: mBadge.id,
      name: mBadge.name,
      type: mBadge.type,
      description: mBadge.description,
      assetName: mBadge.assetName
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
    const mBadge = this.createFromForm();
    if (mBadge.id !== undefined) {
      this.subscribeToSaveResponse(this.mBadgeService.update(mBadge));
    } else {
      this.subscribeToSaveResponse(this.mBadgeService.create(mBadge));
    }
  }

  private createFromForm(): IMBadge {
    const entity = {
      ...new MBadge(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      type: this.editForm.get(['type']).value,
      description: this.editForm.get(['description']).value,
      assetName: this.editForm.get(['assetName']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMBadge>>) {
    result.subscribe((res: HttpResponse<IMBadge>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
