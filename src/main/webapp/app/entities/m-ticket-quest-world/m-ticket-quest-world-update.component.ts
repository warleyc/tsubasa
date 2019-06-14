import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMTicketQuestWorld, MTicketQuestWorld } from 'app/shared/model/m-ticket-quest-world.model';
import { MTicketQuestWorldService } from './m-ticket-quest-world.service';

@Component({
  selector: 'jhi-m-ticket-quest-world-update',
  templateUrl: './m-ticket-quest-world-update.component.html'
})
export class MTicketQuestWorldUpdateComponent implements OnInit {
  mTicketQuestWorld: IMTicketQuestWorld;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    setId: [null, [Validators.required]],
    number: [null, [Validators.required]],
    name: [null, [Validators.required]],
    imagePath: [null, [Validators.required]],
    description: [null, [Validators.required]],
    stageUnlockPattern: [null, [Validators.required]],
    specialRewardContentType: [],
    specialRewardContentId: [],
    isEnableCoop: [null, [Validators.required]],
    isHideDoNotHavingTicket: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mTicketQuestWorldService: MTicketQuestWorldService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTicketQuestWorld }) => {
      this.updateForm(mTicketQuestWorld);
      this.mTicketQuestWorld = mTicketQuestWorld;
    });
  }

  updateForm(mTicketQuestWorld: IMTicketQuestWorld) {
    this.editForm.patchValue({
      id: mTicketQuestWorld.id,
      setId: mTicketQuestWorld.setId,
      number: mTicketQuestWorld.number,
      name: mTicketQuestWorld.name,
      imagePath: mTicketQuestWorld.imagePath,
      description: mTicketQuestWorld.description,
      stageUnlockPattern: mTicketQuestWorld.stageUnlockPattern,
      specialRewardContentType: mTicketQuestWorld.specialRewardContentType,
      specialRewardContentId: mTicketQuestWorld.specialRewardContentId,
      isEnableCoop: mTicketQuestWorld.isEnableCoop,
      isHideDoNotHavingTicket: mTicketQuestWorld.isHideDoNotHavingTicket
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
    const mTicketQuestWorld = this.createFromForm();
    if (mTicketQuestWorld.id !== undefined) {
      this.subscribeToSaveResponse(this.mTicketQuestWorldService.update(mTicketQuestWorld));
    } else {
      this.subscribeToSaveResponse(this.mTicketQuestWorldService.create(mTicketQuestWorld));
    }
  }

  private createFromForm(): IMTicketQuestWorld {
    const entity = {
      ...new MTicketQuestWorld(),
      id: this.editForm.get(['id']).value,
      setId: this.editForm.get(['setId']).value,
      number: this.editForm.get(['number']).value,
      name: this.editForm.get(['name']).value,
      imagePath: this.editForm.get(['imagePath']).value,
      description: this.editForm.get(['description']).value,
      stageUnlockPattern: this.editForm.get(['stageUnlockPattern']).value,
      specialRewardContentType: this.editForm.get(['specialRewardContentType']).value,
      specialRewardContentId: this.editForm.get(['specialRewardContentId']).value,
      isEnableCoop: this.editForm.get(['isEnableCoop']).value,
      isHideDoNotHavingTicket: this.editForm.get(['isHideDoNotHavingTicket']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTicketQuestWorld>>) {
    result.subscribe((res: HttpResponse<IMTicketQuestWorld>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
