import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMQuestCoop } from 'app/shared/model/m-quest-coop.model';
import { MQuestCoopService } from './m-quest-coop.service';

@Component({
  selector: 'jhi-m-quest-coop-delete-dialog',
  templateUrl: './m-quest-coop-delete-dialog.component.html'
})
export class MQuestCoopDeleteDialogComponent {
  mQuestCoop: IMQuestCoop;

  constructor(
    protected mQuestCoopService: MQuestCoopService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mQuestCoopService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mQuestCoopListModification',
        content: 'Deleted an mQuestCoop'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-quest-coop-delete-popup',
  template: ''
})
export class MQuestCoopDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestCoop }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MQuestCoopDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mQuestCoop = mQuestCoop;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-quest-coop', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-quest-coop', { outlets: { popup: null } }]);
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
