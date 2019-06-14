/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTsubasaPointDeleteDialogComponent } from 'app/entities/m-tsubasa-point/m-tsubasa-point-delete-dialog.component';
import { MTsubasaPointService } from 'app/entities/m-tsubasa-point/m-tsubasa-point.service';

describe('Component Tests', () => {
  describe('MTsubasaPoint Management Delete Component', () => {
    let comp: MTsubasaPointDeleteDialogComponent;
    let fixture: ComponentFixture<MTsubasaPointDeleteDialogComponent>;
    let service: MTsubasaPointService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTsubasaPointDeleteDialogComponent]
      })
        .overrideTemplate(MTsubasaPointDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTsubasaPointDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTsubasaPointService);
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
