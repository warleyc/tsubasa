import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMModelUniformUpResource, MModelUniformUpResource } from 'app/shared/model/m-model-uniform-up-resource.model';
import { MModelUniformUpResourceService } from './m-model-uniform-up-resource.service';

@Component({
  selector: 'jhi-m-model-uniform-up-resource-update',
  templateUrl: './m-model-uniform-up-resource-update.component.html'
})
export class MModelUniformUpResourceUpdateComponent implements OnInit {
  mModelUniformUpResource: IMModelUniformUpResource;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    uniformTypeId: [null, [Validators.required]],
    dressingType: [null, [Validators.required]],
    width: [null, [Validators.required]]
  });

  constructor(
    protected mModelUniformUpResourceService: MModelUniformUpResourceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mModelUniformUpResource }) => {
      this.updateForm(mModelUniformUpResource);
      this.mModelUniformUpResource = mModelUniformUpResource;
    });
  }

  updateForm(mModelUniformUpResource: IMModelUniformUpResource) {
    this.editForm.patchValue({
      id: mModelUniformUpResource.id,
      uniformTypeId: mModelUniformUpResource.uniformTypeId,
      dressingType: mModelUniformUpResource.dressingType,
      width: mModelUniformUpResource.width
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mModelUniformUpResource = this.createFromForm();
    if (mModelUniformUpResource.id !== undefined) {
      this.subscribeToSaveResponse(this.mModelUniformUpResourceService.update(mModelUniformUpResource));
    } else {
      this.subscribeToSaveResponse(this.mModelUniformUpResourceService.create(mModelUniformUpResource));
    }
  }

  private createFromForm(): IMModelUniformUpResource {
    const entity = {
      ...new MModelUniformUpResource(),
      id: this.editForm.get(['id']).value,
      uniformTypeId: this.editForm.get(['uniformTypeId']).value,
      dressingType: this.editForm.get(['dressingType']).value,
      width: this.editForm.get(['width']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMModelUniformUpResource>>) {
    result.subscribe((res: HttpResponse<IMModelUniformUpResource>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
