import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGuildLevel } from 'app/shared/model/m-guild-level.model';
import { MGuildLevelService } from './m-guild-level.service';

@Component({
  selector: 'jhi-m-guild-level-delete-dialog',
  templateUrl: './m-guild-level-delete-dialog.component.html'
})
export class MGuildLevelDeleteDialogComponent {
  mGuildLevel: IMGuildLevel;

  constructor(
    protected mGuildLevelService: MGuildLevelService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGuildLevelService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGuildLevelListModification',
        content: 'Deleted an mGuildLevel'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-guild-level-delete-popup',
  template: ''
})
export class MGuildLevelDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuildLevel }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGuildLevelDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mGuildLevel = mGuildLevel;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-guild-level', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-guild-level', { outlets: { popup: null } }]);
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
