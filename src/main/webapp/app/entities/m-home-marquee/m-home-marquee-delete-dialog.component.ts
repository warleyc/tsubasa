import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMHomeMarquee } from 'app/shared/model/m-home-marquee.model';
import { MHomeMarqueeService } from './m-home-marquee.service';

@Component({
  selector: 'jhi-m-home-marquee-delete-dialog',
  templateUrl: './m-home-marquee-delete-dialog.component.html'
})
export class MHomeMarqueeDeleteDialogComponent {
  mHomeMarquee: IMHomeMarquee;

  constructor(
    protected mHomeMarqueeService: MHomeMarqueeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mHomeMarqueeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mHomeMarqueeListModification',
        content: 'Deleted an mHomeMarquee'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-home-marquee-delete-popup',
  template: ''
})
export class MHomeMarqueeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mHomeMarquee }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MHomeMarqueeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mHomeMarquee = mHomeMarquee;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-home-marquee', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-home-marquee', { outlets: { popup: null } }]);
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
