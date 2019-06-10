/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MDummyEmblemDeleteDialogComponent } from 'app/entities/m-dummy-emblem/m-dummy-emblem-delete-dialog.component';
import { MDummyEmblemService } from 'app/entities/m-dummy-emblem/m-dummy-emblem.service';

describe('Component Tests', () => {
  describe('MDummyEmblem Management Delete Component', () => {
    let comp: MDummyEmblemDeleteDialogComponent;
    let fixture: ComponentFixture<MDummyEmblemDeleteDialogComponent>;
    let service: MDummyEmblemService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDummyEmblemDeleteDialogComponent]
      })
        .overrideTemplate(MDummyEmblemDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDummyEmblemDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDummyEmblemService);
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
