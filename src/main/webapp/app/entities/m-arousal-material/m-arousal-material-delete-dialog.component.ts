import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMArousalMaterial } from 'app/shared/model/m-arousal-material.model';
import { MArousalMaterialService } from './m-arousal-material.service';

@Component({
  selector: 'jhi-m-arousal-material-delete-dialog',
  templateUrl: './m-arousal-material-delete-dialog.component.html'
})
export class MArousalMaterialDeleteDialogComponent {
  mArousalMaterial: IMArousalMaterial;

  constructor(
    protected mArousalMaterialService: MArousalMaterialService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mArousalMaterialService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mArousalMaterialListModification',
        content: 'Deleted an mArousalMaterial'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-arousal-material-delete-popup',
  template: ''
})
export class MArousalMaterialDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mArousalMaterial }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MArousalMaterialDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mArousalMaterial = mArousalMaterial;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-arousal-material', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-arousal-material', { outlets: { popup: null } }]);
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
