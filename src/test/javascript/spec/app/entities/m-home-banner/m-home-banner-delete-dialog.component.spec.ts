/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MHomeBannerDeleteDialogComponent } from 'app/entities/m-home-banner/m-home-banner-delete-dialog.component';
import { MHomeBannerService } from 'app/entities/m-home-banner/m-home-banner.service';

describe('Component Tests', () => {
  describe('MHomeBanner Management Delete Component', () => {
    let comp: MHomeBannerDeleteDialogComponent;
    let fixture: ComponentFixture<MHomeBannerDeleteDialogComponent>;
    let service: MHomeBannerService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MHomeBannerDeleteDialogComponent]
      })
        .overrideTemplate(MHomeBannerDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MHomeBannerDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MHomeBannerService);
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
