import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMGachaTicket, MGachaTicket } from 'app/shared/model/m-gacha-ticket.model';
import { MGachaTicketService } from './m-gacha-ticket.service';

@Component({
  selector: 'jhi-m-gacha-ticket-update',
  templateUrl: './m-gacha-ticket-update.component.html'
})
export class MGachaTicketUpdateComponent implements OnInit {
  mGachaTicket: IMGachaTicket;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    shortName: [null, [Validators.required]],
    description: [null, [Validators.required]],
    maxAmount: [null, [Validators.required]],
    thumbnailAssetName: [null, [Validators.required]],
    endAt: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mGachaTicketService: MGachaTicketService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGachaTicket }) => {
      this.updateForm(mGachaTicket);
      this.mGachaTicket = mGachaTicket;
    });
  }

  updateForm(mGachaTicket: IMGachaTicket) {
    this.editForm.patchValue({
      id: mGachaTicket.id,
      name: mGachaTicket.name,
      shortName: mGachaTicket.shortName,
      description: mGachaTicket.description,
      maxAmount: mGachaTicket.maxAmount,
      thumbnailAssetName: mGachaTicket.thumbnailAssetName,
      endAt: mGachaTicket.endAt
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
    const mGachaTicket = this.createFromForm();
    if (mGachaTicket.id !== undefined) {
      this.subscribeToSaveResponse(this.mGachaTicketService.update(mGachaTicket));
    } else {
      this.subscribeToSaveResponse(this.mGachaTicketService.create(mGachaTicket));
    }
  }

  private createFromForm(): IMGachaTicket {
    const entity = {
      ...new MGachaTicket(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      shortName: this.editForm.get(['shortName']).value,
      description: this.editForm.get(['description']).value,
      maxAmount: this.editForm.get(['maxAmount']).value,
      thumbnailAssetName: this.editForm.get(['thumbnailAssetName']).value,
      endAt: this.editForm.get(['endAt']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGachaTicket>>) {
    result.subscribe((res: HttpResponse<IMGachaTicket>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
