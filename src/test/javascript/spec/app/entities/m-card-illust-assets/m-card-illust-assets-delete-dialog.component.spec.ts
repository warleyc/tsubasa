/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MCardIllustAssetsDeleteDialogComponent } from 'app/entities/m-card-illust-assets/m-card-illust-assets-delete-dialog.component';
import { MCardIllustAssetsService } from 'app/entities/m-card-illust-assets/m-card-illust-assets.service';

describe('Component Tests', () => {
  describe('MCardIllustAssets Management Delete Component', () => {
    let comp: MCardIllustAssetsDeleteDialogComponent;
    let fixture: ComponentFixture<MCardIllustAssetsDeleteDialogComponent>;
    let service: MCardIllustAssetsService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardIllustAssetsDeleteDialogComponent]
      })
        .overrideTemplate(MCardIllustAssetsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCardIllustAssetsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCardIllustAssetsService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
