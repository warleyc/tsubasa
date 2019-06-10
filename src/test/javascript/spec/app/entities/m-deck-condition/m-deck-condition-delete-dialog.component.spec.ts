/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MDeckConditionDeleteDialogComponent } from 'app/entities/m-deck-condition/m-deck-condition-delete-dialog.component';
import { MDeckConditionService } from 'app/entities/m-deck-condition/m-deck-condition.service';

describe('Component Tests', () => {
  describe('MDeckCondition Management Delete Component', () => {
    let comp: MDeckConditionDeleteDialogComponent;
    let fixture: ComponentFixture<MDeckConditionDeleteDialogComponent>;
    let service: MDeckConditionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDeckConditionDeleteDialogComponent]
      })
        .overrideTemplate(MDeckConditionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDeckConditionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDeckConditionService);
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
