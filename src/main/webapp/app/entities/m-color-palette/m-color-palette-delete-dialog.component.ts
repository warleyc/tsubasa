import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMColorPalette } from 'app/shared/model/m-color-palette.model';
import { MColorPaletteService } from './m-color-palette.service';

@Component({
  selector: 'jhi-m-color-palette-delete-dialog',
  templateUrl: './m-color-palette-delete-dialog.component.html'
})
export class MColorPaletteDeleteDialogComponent {
  mColorPalette: IMColorPalette;

  constructor(
    protected mColorPaletteService: MColorPaletteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mColorPaletteService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mColorPaletteListModification',
        content: 'Deleted an mColorPalette'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-color-palette-delete-popup',
  template: ''
})
export class MColorPaletteDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mColorPalette }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MColorPaletteDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mColorPalette = mColorPalette;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-color-palette', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-color-palette', { outlets: { popup: null } }]);
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
