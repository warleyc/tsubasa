import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMTeam, MTeam } from 'app/shared/model/m-team.model';
import { MTeamService } from './m-team.service';

@Component({
  selector: 'jhi-m-team-update',
  templateUrl: './m-team-update.component.html'
})
export class MTeamUpdateComponent implements OnInit {
  mTeam: IMTeam;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mTeamService: MTeamService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTeam }) => {
      this.updateForm(mTeam);
      this.mTeam = mTeam;
    });
  }

  updateForm(mTeam: IMTeam) {
    this.editForm.patchValue({
      id: mTeam.id,
      name: mTeam.name
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
    const mTeam = this.createFromForm();
    if (mTeam.id !== undefined) {
      this.subscribeToSaveResponse(this.mTeamService.update(mTeam));
    } else {
      this.subscribeToSaveResponse(this.mTeamService.create(mTeam));
    }
  }

  private createFromForm(): IMTeam {
    const entity = {
      ...new MTeam(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTeam>>) {
    result.subscribe((res: HttpResponse<IMTeam>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
