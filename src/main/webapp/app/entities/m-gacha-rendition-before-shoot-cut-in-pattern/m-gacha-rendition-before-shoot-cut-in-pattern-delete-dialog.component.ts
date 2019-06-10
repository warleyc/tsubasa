import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGachaRenditionBeforeShootCutInPattern } from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-pattern.model';
import { MGachaRenditionBeforeShootCutInPatternService } from './m-gacha-rendition-before-shoot-cut-in-pattern.service';

@Component({
  selector: 'jhi-m-gacha-rendition-before-shoot-cut-in-pattern-delete-dialog',
  templateUrl: './m-gacha-rendition-before-shoot-cut-in-pattern-delete-dialog.component.html'
})
export class MGachaRenditionBeforeShootCutInPatternDeleteDialogComponent {
  mGachaRenditionBeforeShootCutInPattern: IMGachaRenditionBeforeShootCutInPattern;

  constructor(
    protected mGachaRenditionBeforeShootCutInPatternService: MGachaRenditionBeforeShootCutInPatternService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGachaRenditionBeforeShootCutInPatternService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGachaRenditionBeforeShootCutInPatternListModification',
        content: 'Deleted an mGachaRenditionBeforeShootCutInPattern'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-gacha-rendition-before-shoot-cut-in-pattern-delete-popup',
  template: ''
})
export class MGachaRenditionBeforeShootCutInPatternDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionBeforeShootCutInPattern }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGachaRenditionBeforeShootCutInPatternDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGachaRenditionBeforeShootCutInPattern = mGachaRenditionBeforeShootCutInPattern;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-gacha-rendition-before-shoot-cut-in-pattern', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-gacha-rendition-before-shoot-cut-in-pattern', { outlets: { popup: null } }]);
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
