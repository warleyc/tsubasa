import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMDummyEmblem, MDummyEmblem } from 'app/shared/model/m-dummy-emblem.model';
import { MDummyEmblemService } from './m-dummy-emblem.service';
import { IMEmblemParts } from 'app/shared/model/m-emblem-parts.model';
import { MEmblemPartsService } from 'app/entities/m-emblem-parts';

@Component({
  selector: 'jhi-m-dummy-emblem-update',
  templateUrl: './m-dummy-emblem-update.component.html'
})
export class MDummyEmblemUpdateComponent implements OnInit {
  mDummyEmblem: IMDummyEmblem;
  isSaving: boolean;

  memblemparts: IMEmblemParts[];

  editForm = this.fb.group({
    id: [],
    backgroundId: [null, [Validators.required]],
    backgroundColor: [null, [Validators.required]],
    upperId: [],
    upperColor: [],
    middleId: [null, [Validators.required]],
    middleColor: [null, [Validators.required]],
    lowerId: [],
    lowerColor: [],
    memblempartsId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mDummyEmblemService: MDummyEmblemService,
    protected mEmblemPartsService: MEmblemPartsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mDummyEmblem }) => {
      this.updateForm(mDummyEmblem);
      this.mDummyEmblem = mDummyEmblem;
    });
    this.mEmblemPartsService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMEmblemParts[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMEmblemParts[]>) => response.body)
      )
      .subscribe((res: IMEmblemParts[]) => (this.memblemparts = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mDummyEmblem: IMDummyEmblem) {
    this.editForm.patchValue({
      id: mDummyEmblem.id,
      backgroundId: mDummyEmblem.backgroundId,
      backgroundColor: mDummyEmblem.backgroundColor,
      upperId: mDummyEmblem.upperId,
      upperColor: mDummyEmblem.upperColor,
      middleId: mDummyEmblem.middleId,
      middleColor: mDummyEmblem.middleColor,
      lowerId: mDummyEmblem.lowerId,
      lowerColor: mDummyEmblem.lowerColor,
      memblempartsId: mDummyEmblem.memblempartsId
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
    const mDummyEmblem = this.createFromForm();
    if (mDummyEmblem.id !== undefined) {
      this.subscribeToSaveResponse(this.mDummyEmblemService.update(mDummyEmblem));
    } else {
      this.subscribeToSaveResponse(this.mDummyEmblemService.create(mDummyEmblem));
    }
  }

  private createFromForm(): IMDummyEmblem {
    const entity = {
      ...new MDummyEmblem(),
      id: this.editForm.get(['id']).value,
      backgroundId: this.editForm.get(['backgroundId']).value,
      backgroundColor: this.editForm.get(['backgroundColor']).value,
      upperId: this.editForm.get(['upperId']).value,
      upperColor: this.editForm.get(['upperColor']).value,
      middleId: this.editForm.get(['middleId']).value,
      middleColor: this.editForm.get(['middleColor']).value,
      lowerId: this.editForm.get(['lowerId']).value,
      lowerColor: this.editForm.get(['lowerColor']).value,
      memblempartsId: this.editForm.get(['memblempartsId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMDummyEmblem>>) {
    result.subscribe((res: HttpResponse<IMDummyEmblem>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMEmblemPartsById(index: number, item: IMEmblemParts) {
    return item.id;
  }
}
