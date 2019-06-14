/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionTradeSignDeleteDialogComponent } from 'app/entities/m-gacha-rendition-trade-sign/m-gacha-rendition-trade-sign-delete-dialog.component';
import { MGachaRenditionTradeSignService } from 'app/entities/m-gacha-rendition-trade-sign/m-gacha-rendition-trade-sign.service';

describe('Component Tests', () => {
  describe('MGachaRenditionTradeSign Management Delete Component', () => {
    let comp: MGachaRenditionTradeSignDeleteDialogComponent;
    let fixture: ComponentFixture<MGachaRenditionTradeSignDeleteDialogComponent>;
    let service: MGachaRenditionTradeSignService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionTradeSignDeleteDialogComponent]
      })
        .overrideTemplate(MGachaRenditionTradeSignDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionTradeSignDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionTradeSignService);
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
