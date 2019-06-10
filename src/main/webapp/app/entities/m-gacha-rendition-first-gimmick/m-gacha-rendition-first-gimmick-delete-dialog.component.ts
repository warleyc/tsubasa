import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGachaRenditionFirstGimmick } from 'app/shared/model/m-gacha-rendition-first-gimmick.model';
import { MGachaRenditionFirstGimmickService } from './m-gacha-rendition-first-gimmick.service';

@Component({
  selector: 'jhi-m-gacha-rendition-first-gimmick-delete-dialog',
  templateUrl: './m-gacha-rendition-first-gimmick-delete-dialog.component.html'
})
export class MGachaRenditionFirstGimmickDeleteDialogComponent {
  mGachaRenditionFirstGimmick: IMGachaRenditionFirstGimmick;

  constructor(
    protected mGachaRenditionFirstGimmickService: MGachaRenditionFirstGimmickService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGachaRenditionFirstGimmickService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGachaRenditionFirstGimmickListModification',
        content: 'Deleted an mGachaRenditionFirstGimmick'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-gacha-rendition-first-gimmick-delete-popup',
  template: ''
})
export class MGachaRenditionFirstGimmickDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionFirstGimmick }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGachaRenditionFirstGimmickDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGachaRenditionFirstGimmick = mGachaRenditionFirstGimmick;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-gacha-rendition-first-gimmick', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-gacha-rendition-first-gimmick', { outlets: { popup: null } }]);
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
