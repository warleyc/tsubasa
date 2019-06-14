import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMModelUniformBottomResource, MModelUniformBottomResource } from 'app/shared/model/m-model-uniform-bottom-resource.model';
import { MModelUniformBottomResourceService } from './m-model-uniform-bottom-resource.service';

@Component({
  selector: 'jhi-m-model-uniform-bottom-resource-update',
  templateUrl: './m-model-uniform-bottom-resource-update.component.html'
})
export class MModelUniformBottomResourceUpdateComponent implements OnInit {
  mModelUniformBottomResource: IMModelUniformBottomResource;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    uniformTypeId: [null, [Validators.required]],
    dressingType: [null, [Validators.required]],
    width: [null, [Validators.required]]
  });

  constructor(
    protected mModelUniformBottomResourceService: MModelUniformBottomResourceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mModelUniformBottomResource }) => {
      this.updateForm(mModelUniformBottomResource);
      this.mModelUniformBottomResource = mModelUniformBottomResource;
    });
  }

  updateForm(mModelUniformBottomResource: IMModelUniformBottomResource) {
    this.editForm.patchValue({
      id: mModelUniformBottomResource.id,
      uniformTypeId: mModelUniformBottomResource.uniformTypeId,
      dressingType: mModelUniformBottomResource.dressingType,
      width: mModelUniformBottomResource.width
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mModelUniformBottomResource = this.createFromForm();
    if (mModelUniformBottomResource.id !== undefined) {
      this.subscribeToSaveResponse(this.mModelUniformBottomResourceService.update(mModelUniformBottomResource));
    } else {
      this.subscribeToSaveResponse(this.mModelUniformBottomResourceService.create(mModelUniformBottomResource));
    }
  }

  private createFromForm(): IMModelUniformBottomResource {
    const entity = {
      ...new MModelUniformBottomResource(),
      id: this.editForm.get(['id']).value,
      uniformTypeId: this.editForm.get(['uniformTypeId']).value,
      dressingType: this.editForm.get(['dressingType']).value,
      width: this.editForm.get(['width']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMModelUniformBottomResource>>) {
    result.subscribe(
      (res: HttpResponse<IMModelUniformBottomResource>) => this.onSaveSuccess(),
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
}
