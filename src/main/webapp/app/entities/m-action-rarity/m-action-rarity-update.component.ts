import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMActionRarity, MActionRarity } from 'app/shared/model/m-action-rarity.model';
import { MActionRarityService } from './m-action-rarity.service';

@Component({
  selector: 'jhi-m-action-rarity-update',
  templateUrl: './m-action-rarity-update.component.html'
})
export class MActionRarityUpdateComponent implements OnInit {
  mActionRarity: IMActionRarity;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    rarity: [null, [Validators.required]],
    name: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mActionRarityService: MActionRarityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mActionRarity }) => {
      this.updateForm(mActionRarity);
      this.mActionRarity = mActionRarity;
    });
  }

  updateForm(mActionRarity: IMActionRarity) {
    this.editForm.patchValue({
      id: mActionRarity.id,
      rarity: mActionRarity.rarity,
      name: mActionRarity.name
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
    const mActionRarity = this.createFromForm();
    if (mActionRarity.id !== undefined) {
      this.subscribeToSaveResponse(this.mActionRarityService.update(mActionRarity));
    } else {
      this.subscribeToSaveResponse(this.mActionRarityService.create(mActionRarity));
    }
  }

  private createFromForm(): IMActionRarity {
    const entity = {
      ...new MActionRarity(),
      id: this.editForm.get(['id']).value,
      rarity: this.editForm.get(['rarity']).value,
      name: this.editForm.get(['name']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMActionRarity>>) {
    result.subscribe((res: HttpResponse<IMActionRarity>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
