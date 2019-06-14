import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMStoryResourceMapping, MStoryResourceMapping } from 'app/shared/model/m-story-resource-mapping.model';
import { MStoryResourceMappingService } from './m-story-resource-mapping.service';

@Component({
  selector: 'jhi-m-story-resource-mapping-update',
  templateUrl: './m-story-resource-mapping-update.component.html'
})
export class MStoryResourceMappingUpdateComponent implements OnInit {
  mStoryResourceMapping: IMStoryResourceMapping;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    lang: [null, [Validators.required]],
    scriptName: [null, [Validators.required]],
    ids: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mStoryResourceMappingService: MStoryResourceMappingService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mStoryResourceMapping }) => {
      this.updateForm(mStoryResourceMapping);
      this.mStoryResourceMapping = mStoryResourceMapping;
    });
  }

  updateForm(mStoryResourceMapping: IMStoryResourceMapping) {
    this.editForm.patchValue({
      id: mStoryResourceMapping.id,
      lang: mStoryResourceMapping.lang,
      scriptName: mStoryResourceMapping.scriptName,
      ids: mStoryResourceMapping.ids
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
    const mStoryResourceMapping = this.createFromForm();
    if (mStoryResourceMapping.id !== undefined) {
      this.subscribeToSaveResponse(this.mStoryResourceMappingService.update(mStoryResourceMapping));
    } else {
      this.subscribeToSaveResponse(this.mStoryResourceMappingService.create(mStoryResourceMapping));
    }
  }

  private createFromForm(): IMStoryResourceMapping {
    const entity = {
      ...new MStoryResourceMapping(),
      id: this.editForm.get(['id']).value,
      lang: this.editForm.get(['lang']).value,
      scriptName: this.editForm.get(['scriptName']).value,
      ids: this.editForm.get(['ids']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMStoryResourceMapping>>) {
    result.subscribe((res: HttpResponse<IMStoryResourceMapping>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
