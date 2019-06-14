import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMModelCard, MModelCard } from 'app/shared/model/m-model-card.model';
import { MModelCardService } from './m-model-card.service';

@Component({
  selector: 'jhi-m-model-card-update',
  templateUrl: './m-model-card-update.component.html'
})
export class MModelCardUpdateComponent implements OnInit {
  mModelCard: IMModelCard;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    hairModel: [null, [Validators.required]],
    hairTexture: [null, [Validators.required]],
    headModel: [null, [Validators.required]],
    headTexture: [null, [Validators.required]],
    skinTexture: [null, [Validators.required]],
    shoesModel: [null, [Validators.required]],
    shoesTexture: [null, [Validators.required]],
    globeTexture: [],
    uniqueModel: [],
    uniqueTexture: [],
    dressingTypeUp: [null, [Validators.required]],
    dressingTypeBottom: [null, [Validators.required]],
    height: [null, [Validators.required]],
    width: [null, [Validators.required]]
  });

  constructor(protected mModelCardService: MModelCardService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mModelCard }) => {
      this.updateForm(mModelCard);
      this.mModelCard = mModelCard;
    });
  }

  updateForm(mModelCard: IMModelCard) {
    this.editForm.patchValue({
      id: mModelCard.id,
      hairModel: mModelCard.hairModel,
      hairTexture: mModelCard.hairTexture,
      headModel: mModelCard.headModel,
      headTexture: mModelCard.headTexture,
      skinTexture: mModelCard.skinTexture,
      shoesModel: mModelCard.shoesModel,
      shoesTexture: mModelCard.shoesTexture,
      globeTexture: mModelCard.globeTexture,
      uniqueModel: mModelCard.uniqueModel,
      uniqueTexture: mModelCard.uniqueTexture,
      dressingTypeUp: mModelCard.dressingTypeUp,
      dressingTypeBottom: mModelCard.dressingTypeBottom,
      height: mModelCard.height,
      width: mModelCard.width
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mModelCard = this.createFromForm();
    if (mModelCard.id !== undefined) {
      this.subscribeToSaveResponse(this.mModelCardService.update(mModelCard));
    } else {
      this.subscribeToSaveResponse(this.mModelCardService.create(mModelCard));
    }
  }

  private createFromForm(): IMModelCard {
    const entity = {
      ...new MModelCard(),
      id: this.editForm.get(['id']).value,
      hairModel: this.editForm.get(['hairModel']).value,
      hairTexture: this.editForm.get(['hairTexture']).value,
      headModel: this.editForm.get(['headModel']).value,
      headTexture: this.editForm.get(['headTexture']).value,
      skinTexture: this.editForm.get(['skinTexture']).value,
      shoesModel: this.editForm.get(['shoesModel']).value,
      shoesTexture: this.editForm.get(['shoesTexture']).value,
      globeTexture: this.editForm.get(['globeTexture']).value,
      uniqueModel: this.editForm.get(['uniqueModel']).value,
      uniqueTexture: this.editForm.get(['uniqueTexture']).value,
      dressingTypeUp: this.editForm.get(['dressingTypeUp']).value,
      dressingTypeBottom: this.editForm.get(['dressingTypeBottom']).value,
      height: this.editForm.get(['height']).value,
      width: this.editForm.get(['width']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMModelCard>>) {
    result.subscribe((res: HttpResponse<IMModelCard>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
