import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTrainingCard } from 'app/shared/model/m-training-card.model';
import { MTrainingCardService } from './m-training-card.service';

@Component({
  selector: 'jhi-m-training-card-delete-dialog',
  templateUrl: './m-training-card-delete-dialog.component.html'
})
export class MTrainingCardDeleteDialogComponent {
  mTrainingCard: IMTrainingCard;

  constructor(
    protected mTrainingCardService: MTrainingCardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTrainingCardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTrainingCardListModification',
        content: 'Deleted an mTrainingCard'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-training-card-delete-popup',
  template: ''
})
export class MTrainingCardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTrainingCard }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTrainingCardDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mTrainingCard = mTrainingCard;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-training-card', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-training-card', { outlets: { popup: null } }]);
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
