/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionExtraCutinDeleteDialogComponent } from 'app/entities/m-gacha-rendition-extra-cutin/m-gacha-rendition-extra-cutin-delete-dialog.component';
import { MGachaRenditionExtraCutinService } from 'app/entities/m-gacha-rendition-extra-cutin/m-gacha-rendition-extra-cutin.service';

describe('Component Tests', () => {
  describe('MGachaRenditionExtraCutin Management Delete Component', () => {
    let comp: MGachaRenditionExtraCutinDeleteDialogComponent;
    let fixture: ComponentFixture<MGachaRenditionExtraCutinDeleteDialogComponent>;
    let service: MGachaRenditionExtraCutinService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionExtraCutinDeleteDialogComponent]
      })
        .overrideTemplate(MGachaRenditionExtraCutinDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionExtraCutinDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionExtraCutinService);
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
