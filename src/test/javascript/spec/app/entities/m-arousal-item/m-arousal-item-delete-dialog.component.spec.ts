/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MArousalItemDeleteDialogComponent } from 'app/entities/m-arousal-item/m-arousal-item-delete-dialog.component';
import { MArousalItemService } from 'app/entities/m-arousal-item/m-arousal-item.service';

describe('Component Tests', () => {
  describe('MArousalItem Management Delete Component', () => {
    let comp: MArousalItemDeleteDialogComponent;
    let fixture: ComponentFixture<MArousalItemDeleteDialogComponent>;
    let service: MArousalItemService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MArousalItemDeleteDialogComponent]
      })
        .overrideTemplate(MArousalItemDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MArousalItemDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MArousalItemService);
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
