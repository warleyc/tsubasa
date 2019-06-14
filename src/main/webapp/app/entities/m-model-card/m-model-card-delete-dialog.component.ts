import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMModelCard } from 'app/shared/model/m-model-card.model';
import { MModelCardService } from './m-model-card.service';

@Component({
  selector: 'jhi-m-model-card-delete-dialog',
  templateUrl: './m-model-card-delete-dialog.component.html'
})
export class MModelCardDeleteDialogComponent {
  mModelCard: IMModelCard;

  constructor(
    protected mModelCardService: MModelCardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mModelCardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mModelCardListModification',
        content: 'Deleted an mModelCard'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-model-card-delete-popup',
  template: ''
})
export class MModelCardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mModelCard }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MModelCardDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mModelCard = mModelCard;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-model-card', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-model-card', { outlets: { popup: null } }]);
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
