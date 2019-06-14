import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMTeamEffectRarity, MTeamEffectRarity } from 'app/shared/model/m-team-effect-rarity.model';
import { MTeamEffectRarityService } from './m-team-effect-rarity.service';

@Component({
  selector: 'jhi-m-team-effect-rarity-update',
  templateUrl: './m-team-effect-rarity-update.component.html'
})
export class MTeamEffectRarityUpdateComponent implements OnInit {
  mTeamEffectRarity: IMTeamEffectRarity;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    rarity: [null, [Validators.required]],
    name: [null, [Validators.required]],
    maxLevel: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mTeamEffectRarityService: MTeamEffectRarityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTeamEffectRarity }) => {
      this.updateForm(mTeamEffectRarity);
      this.mTeamEffectRarity = mTeamEffectRarity;
    });
  }

  updateForm(mTeamEffectRarity: IMTeamEffectRarity) {
    this.editForm.patchValue({
      id: mTeamEffectRarity.id,
      rarity: mTeamEffectRarity.rarity,
      name: mTeamEffectRarity.name,
      maxLevel: mTeamEffectRarity.maxLevel
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
    const mTeamEffectRarity = this.createFromForm();
    if (mTeamEffectRarity.id !== undefined) {
      this.subscribeToSaveResponse(this.mTeamEffectRarityService.update(mTeamEffectRarity));
    } else {
      this.subscribeToSaveResponse(this.mTeamEffectRarityService.create(mTeamEffectRarity));
    }
  }

  private createFromForm(): IMTeamEffectRarity {
    const entity = {
      ...new MTeamEffectRarity(),
      id: this.editForm.get(['id']).value,
      rarity: this.editForm.get(['rarity']).value,
      name: this.editForm.get(['name']).value,
      maxLevel: this.editForm.get(['maxLevel']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTeamEffectRarity>>) {
    result.subscribe((res: HttpResponse<IMTeamEffectRarity>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
