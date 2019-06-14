import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMGuildTopBanner, MGuildTopBanner } from 'app/shared/model/m-guild-top-banner.model';
import { MGuildTopBannerService } from './m-guild-top-banner.service';

@Component({
  selector: 'jhi-m-guild-top-banner-update',
  templateUrl: './m-guild-top-banner-update.component.html'
})
export class MGuildTopBannerUpdateComponent implements OnInit {
  mGuildTopBanner: IMGuildTopBanner;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    assetPath: [null, [Validators.required]],
    guildBannerType: [null, [Validators.required]],
    startAt: [null, [Validators.required]],
    endAt: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mGuildTopBannerService: MGuildTopBannerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGuildTopBanner }) => {
      this.updateForm(mGuildTopBanner);
      this.mGuildTopBanner = mGuildTopBanner;
    });
  }

  updateForm(mGuildTopBanner: IMGuildTopBanner) {
    this.editForm.patchValue({
      id: mGuildTopBanner.id,
      assetPath: mGuildTopBanner.assetPath,
      guildBannerType: mGuildTopBanner.guildBannerType,
      startAt: mGuildTopBanner.startAt,
      endAt: mGuildTopBanner.endAt
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
    const mGuildTopBanner = this.createFromForm();
    if (mGuildTopBanner.id !== undefined) {
      this.subscribeToSaveResponse(this.mGuildTopBannerService.update(mGuildTopBanner));
    } else {
      this.subscribeToSaveResponse(this.mGuildTopBannerService.create(mGuildTopBanner));
    }
  }

  private createFromForm(): IMGuildTopBanner {
    const entity = {
      ...new MGuildTopBanner(),
      id: this.editForm.get(['id']).value,
      assetPath: this.editForm.get(['assetPath']).value,
      guildBannerType: this.editForm.get(['guildBannerType']).value,
      startAt: this.editForm.get(['startAt']).value,
      endAt: this.editForm.get(['endAt']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGuildTopBanner>>) {
    result.subscribe((res: HttpResponse<IMGuildTopBanner>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
