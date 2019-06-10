import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGachaRenditionBeforeShootCutInCharacterNum } from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-character-num.model';
import { MGachaRenditionBeforeShootCutInCharacterNumService } from './m-gacha-rendition-before-shoot-cut-in-character-num.service';

@Component({
  selector: 'jhi-m-gacha-rendition-before-shoot-cut-in-character-num-delete-dialog',
  templateUrl: './m-gacha-rendition-before-shoot-cut-in-character-num-delete-dialog.component.html'
})
export class MGachaRenditionBeforeShootCutInCharacterNumDeleteDialogComponent {
  mGachaRenditionBeforeShootCutInCharacterNum: IMGachaRenditionBeforeShootCutInCharacterNum;

  constructor(
    protected mGachaRenditionBeforeShootCutInCharacterNumService: MGachaRenditionBeforeShootCutInCharacterNumService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGachaRenditionBeforeShootCutInCharacterNumService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGachaRenditionBeforeShootCutInCharacterNumListModification',
        content: 'Deleted an mGachaRenditionBeforeShootCutInCharacterNum'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-gacha-rendition-before-shoot-cut-in-character-num-delete-popup',
  template: ''
})
export class MGachaRenditionBeforeShootCutInCharacterNumDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionBeforeShootCutInCharacterNum }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGachaRenditionBeforeShootCutInCharacterNumDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGachaRenditionBeforeShootCutInCharacterNum = mGachaRenditionBeforeShootCutInCharacterNum;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-gacha-rendition-before-shoot-cut-in-character-num', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-gacha-rendition-before-shoot-cut-in-character-num', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
