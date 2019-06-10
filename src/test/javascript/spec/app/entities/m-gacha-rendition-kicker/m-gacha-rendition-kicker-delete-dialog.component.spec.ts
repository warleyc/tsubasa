/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionKickerDeleteDialogComponent } from 'app/entities/m-gacha-rendition-kicker/m-gacha-rendition-kicker-delete-dialog.component';
import { MGachaRenditionKickerService } from 'app/entities/m-gacha-rendition-kicker/m-gacha-rendition-kicker.service';

describe('Component Tests', () => {
  describe('MGachaRenditionKicker Management Delete Component', () => {
    let comp: MGachaRenditionKickerDeleteDialogComponent;
    let fixture: ComponentFixture<MGachaRenditionKickerDeleteDialogComponent>;
    let service: MGachaRenditionKickerService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionKickerDeleteDialogComponent]
      })
        .overrideTemplate(MGachaRenditionKickerDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionKickerDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionKickerService);
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
