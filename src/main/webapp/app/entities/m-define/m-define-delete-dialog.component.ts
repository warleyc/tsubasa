import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDefine } from 'app/shared/model/m-define.model';
import { MDefineService } from './m-define.service';

@Component({
  selector: 'jhi-m-define-delete-dialog',
  templateUrl: './m-define-delete-dialog.component.html'
})
export class MDefineDeleteDialogComponent {
  mDefine: IMDefine;

  constructor(protected mDefineService: MDefineService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDefineService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDefineListModification',
        content: 'Deleted an mDefine'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-define-delete-popup',
  template: ''
})
export class MDefineDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDefine }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDefineDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mDefine = mDefine;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-define', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-define', { outlets: { popup: null } }]);
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
