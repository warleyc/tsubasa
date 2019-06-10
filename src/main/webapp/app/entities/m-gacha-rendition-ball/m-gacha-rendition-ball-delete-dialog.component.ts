import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGachaRenditionBall } from 'app/shared/model/m-gacha-rendition-ball.model';
import { MGachaRenditionBallService } from './m-gacha-rendition-ball.service';

@Component({
  selector: 'jhi-m-gacha-rendition-ball-delete-dialog',
  templateUrl: './m-gacha-rendition-ball-delete-dialog.component.html'
})
export class MGachaRenditionBallDeleteDialogComponent {
  mGachaRenditionBall: IMGachaRenditionBall;

  constructor(
    protected mGachaRenditionBallService: MGachaRenditionBallService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGachaRenditionBallService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGachaRenditionBallListModification',
        content: 'Deleted an mGachaRenditionBall'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-gacha-rendition-ball-delete-popup',
  template: ''
})
export class MGachaRenditionBallDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionBall }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGachaRenditionBallDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGachaRenditionBall = mGachaRenditionBall;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-gacha-rendition-ball', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-gacha-rendition-ball', { outlets: { popup: null } }]);
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
