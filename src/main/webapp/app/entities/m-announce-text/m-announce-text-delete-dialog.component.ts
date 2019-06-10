import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMAnnounceText } from 'app/shared/model/m-announce-text.model';
import { MAnnounceTextService } from './m-announce-text.service';

@Component({
  selector: 'jhi-m-announce-text-delete-dialog',
  templateUrl: './m-announce-text-delete-dialog.component.html'
})
export class MAnnounceTextDeleteDialogComponent {
  mAnnounceText: IMAnnounceText;

  constructor(
    protected mAnnounceTextService: MAnnounceTextService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mAnnounceTextService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mAnnounceTextListModification',
        content: 'Deleted an mAnnounceText'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-announce-text-delete-popup',
  template: ''
})
export class MAnnounceTextDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAnnounceText }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MAnnounceTextDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mAnnounceText = mAnnounceText;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-announce-text', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-announce-text', { outlets: { popup: null } }]);
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
