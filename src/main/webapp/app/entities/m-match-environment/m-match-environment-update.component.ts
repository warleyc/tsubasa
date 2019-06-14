import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMMatchEnvironment, MMatchEnvironment } from 'app/shared/model/m-match-environment.model';
import { MMatchEnvironmentService } from './m-match-environment.service';

@Component({
  selector: 'jhi-m-match-environment-update',
  templateUrl: './m-match-environment-update.component.html'
})
export class MMatchEnvironmentUpdateComponent implements OnInit {
  mMatchEnvironment: IMMatchEnvironment;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    category: [null, [Validators.required]],
    skyTex: [null, [Validators.required]],
    ball: [null, [Validators.required]],
    stadium: [null, [Validators.required]],
    rainyAssetName: [],
    isPlayRainySound: [null, [Validators.required]],
    offenseStartBgm: [null, [Validators.required]],
    offenseStopBgm: [null, [Validators.required]],
    defenseStartBgm: [null, [Validators.required]],
    defenseStopBgm: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mMatchEnvironmentService: MMatchEnvironmentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMatchEnvironment }) => {
      this.updateForm(mMatchEnvironment);
      this.mMatchEnvironment = mMatchEnvironment;
    });
  }

  updateForm(mMatchEnvironment: IMMatchEnvironment) {
    this.editForm.patchValue({
      id: mMatchEnvironment.id,
      category: mMatchEnvironment.category,
      skyTex: mMatchEnvironment.skyTex,
      ball: mMatchEnvironment.ball,
      stadium: mMatchEnvironment.stadium,
      rainyAssetName: mMatchEnvironment.rainyAssetName,
      isPlayRainySound: mMatchEnvironment.isPlayRainySound,
      offenseStartBgm: mMatchEnvironment.offenseStartBgm,
      offenseStopBgm: mMatchEnvironment.offenseStopBgm,
      defenseStartBgm: mMatchEnvironment.defenseStartBgm,
      defenseStopBgm: mMatchEnvironment.defenseStopBgm
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
    const mMatchEnvironment = this.createFromForm();
    if (mMatchEnvironment.id !== undefined) {
      this.subscribeToSaveResponse(this.mMatchEnvironmentService.update(mMatchEnvironment));
    } else {
      this.subscribeToSaveResponse(this.mMatchEnvironmentService.create(mMatchEnvironment));
    }
  }

  private createFromForm(): IMMatchEnvironment {
    const entity = {
      ...new MMatchEnvironment(),
      id: this.editForm.get(['id']).value,
      category: this.editForm.get(['category']).value,
      skyTex: this.editForm.get(['skyTex']).value,
      ball: this.editForm.get(['ball']).value,
      stadium: this.editForm.get(['stadium']).value,
      rainyAssetName: this.editForm.get(['rainyAssetName']).value,
      isPlayRainySound: this.editForm.get(['isPlayRainySound']).value,
      offenseStartBgm: this.editForm.get(['offenseStartBgm']).value,
      offenseStopBgm: this.editForm.get(['offenseStopBgm']).value,
      defenseStartBgm: this.editForm.get(['defenseStartBgm']).value,
      defenseStopBgm: this.editForm.get(['defenseStopBgm']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMatchEnvironment>>) {
    result.subscribe((res: HttpResponse<IMMatchEnvironment>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
