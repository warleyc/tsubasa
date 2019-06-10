import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMEncountersBonus } from 'app/shared/model/m-encounters-bonus.model';
import { MEncountersBonusService } from './m-encounters-bonus.service';

@Component({
  selector: 'jhi-m-encounters-bonus-delete-dialog',
  templateUrl: './m-encounters-bonus-delete-dialog.component.html'
})
export class MEncountersBonusDeleteDialogComponent {
  mEncountersBonus: IMEncountersBonus;

  constructor(
    protected mEncountersBonusService: MEncountersBonusService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mEncountersBonusService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mEncountersBonusListModification',
        content: 'Deleted an mEncountersBonus'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-encounters-bonus-delete-popup',
  template: ''
})
export class MEncountersBonusDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mEncountersBonus }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MEncountersBonusDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mEncountersBonus = mEncountersBonus;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-encounters-bonus', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-encounters-bonus', { outlets: { popup: null } }]);
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
