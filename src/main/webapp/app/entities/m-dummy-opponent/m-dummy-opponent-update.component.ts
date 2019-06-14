import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMDummyOpponent, MDummyOpponent } from 'app/shared/model/m-dummy-opponent.model';
import { MDummyOpponentService } from './m-dummy-opponent.service';
import { IMNpcDeck } from 'app/shared/model/m-npc-deck.model';
import { MNpcDeckService } from 'app/entities/m-npc-deck';

@Component({
  selector: 'jhi-m-dummy-opponent-update',
  templateUrl: './m-dummy-opponent-update.component.html'
})
export class MDummyOpponentUpdateComponent implements OnInit {
  mDummyOpponent: IMDummyOpponent;
  isSaving: boolean;

  mnpcdecks: IMNpcDeck[];

  editForm = this.fb.group({
    id: [],
    npcDeckId: [null, [Validators.required]],
    totalParameter: [null, [Validators.required]],
    name: [null, [Validators.required]],
    rank: [null, [Validators.required]],
    emblemId: [null, [Validators.required]],
    fpUniformUpId: [null, [Validators.required]],
    fpUniformUpColor: [],
    fpUniformBottomId: [null, [Validators.required]],
    fpUniformBottomColor: [],
    gkUniformUpId: [null, [Validators.required]],
    gkUniformUpColor: [],
    gkUniformBottomId: [null, [Validators.required]],
    gkUniformBottomColor: [],
    mnpcdeckId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mDummyOpponentService: MDummyOpponentService,
    protected mNpcDeckService: MNpcDeckService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mDummyOpponent }) => {
      this.updateForm(mDummyOpponent);
      this.mDummyOpponent = mDummyOpponent;
    });
    this.mNpcDeckService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMNpcDeck[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMNpcDeck[]>) => response.body)
      )
      .subscribe((res: IMNpcDeck[]) => (this.mnpcdecks = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mDummyOpponent: IMDummyOpponent) {
    this.editForm.patchValue({
      id: mDummyOpponent.id,
      npcDeckId: mDummyOpponent.npcDeckId,
      totalParameter: mDummyOpponent.totalParameter,
      name: mDummyOpponent.name,
      rank: mDummyOpponent.rank,
      emblemId: mDummyOpponent.emblemId,
      fpUniformUpId: mDummyOpponent.fpUniformUpId,
      fpUniformUpColor: mDummyOpponent.fpUniformUpColor,
      fpUniformBottomId: mDummyOpponent.fpUniformBottomId,
      fpUniformBottomColor: mDummyOpponent.fpUniformBottomColor,
      gkUniformUpId: mDummyOpponent.gkUniformUpId,
      gkUniformUpColor: mDummyOpponent.gkUniformUpColor,
      gkUniformBottomId: mDummyOpponent.gkUniformBottomId,
      gkUniformBottomColor: mDummyOpponent.gkUniformBottomColor,
      mnpcdeckId: mDummyOpponent.mnpcdeckId
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
    const mDummyOpponent = this.createFromForm();
    if (mDummyOpponent.id !== undefined) {
      this.subscribeToSaveResponse(this.mDummyOpponentService.update(mDummyOpponent));
    } else {
      this.subscribeToSaveResponse(this.mDummyOpponentService.create(mDummyOpponent));
    }
  }

  private createFromForm(): IMDummyOpponent {
    const entity = {
      ...new MDummyOpponent(),
      id: this.editForm.get(['id']).value,
      npcDeckId: this.editForm.get(['npcDeckId']).value,
      totalParameter: this.editForm.get(['totalParameter']).value,
      name: this.editForm.get(['name']).value,
      rank: this.editForm.get(['rank']).value,
      emblemId: this.editForm.get(['emblemId']).value,
      fpUniformUpId: this.editForm.get(['fpUniformUpId']).value,
      fpUniformUpColor: this.editForm.get(['fpUniformUpColor']).value,
      fpUniformBottomId: this.editForm.get(['fpUniformBottomId']).value,
      fpUniformBottomColor: this.editForm.get(['fpUniformBottomColor']).value,
      gkUniformUpId: this.editForm.get(['gkUniformUpId']).value,
      gkUniformUpColor: this.editForm.get(['gkUniformUpColor']).value,
      gkUniformBottomId: this.editForm.get(['gkUniformBottomId']).value,
      gkUniformBottomColor: this.editForm.get(['gkUniformBottomColor']).value,
      mnpcdeckId: this.editForm.get(['mnpcdeckId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMDummyOpponent>>) {
    result.subscribe((res: HttpResponse<IMDummyOpponent>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMNpcDeckById(index: number, item: IMNpcDeck) {
    return item.id;
  }
}
