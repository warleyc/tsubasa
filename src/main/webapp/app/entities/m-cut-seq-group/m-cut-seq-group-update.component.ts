import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMCutSeqGroup, MCutSeqGroup } from 'app/shared/model/m-cut-seq-group.model';
import { MCutSeqGroupService } from './m-cut-seq-group.service';

@Component({
  selector: 'jhi-m-cut-seq-group-update',
  templateUrl: './m-cut-seq-group-update.component.html'
})
export class MCutSeqGroupUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    key: [null, [Validators.required]],
    param: [null, [Validators.required]],
    cutSequenceText: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mCutSeqGroupService: MCutSeqGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mCutSeqGroup }) => {
      this.updateForm(mCutSeqGroup);
    });
  }

  updateForm(mCutSeqGroup: IMCutSeqGroup) {
    this.editForm.patchValue({
      id: mCutSeqGroup.id,
      key: mCutSeqGroup.key,
      param: mCutSeqGroup.param,
      cutSequenceText: mCutSeqGroup.cutSequenceText
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
    const mCutSeqGroup = this.createFromForm();
    if (mCutSeqGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mCutSeqGroupService.update(mCutSeqGroup));
    } else {
      this.subscribeToSaveResponse(this.mCutSeqGroupService.create(mCutSeqGroup));
    }
  }

  private createFromForm(): IMCutSeqGroup {
    const entity = {
      ...new MCutSeqGroup(),
      id: this.editForm.get(['id']).value,
      key: this.editForm.get(['key']).value,
      param: this.editForm.get(['param']).value,
      cutSequenceText: this.editForm.get(['cutSequenceText']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMCutSeqGroup>>) {
    result.subscribe((res: HttpResponse<IMCutSeqGroup>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
