/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryEsDeleteDialogComponent } from 'app/entities/m-dictionary-es/m-dictionary-es-delete-dialog.component';
import { MDictionaryEsService } from 'app/entities/m-dictionary-es/m-dictionary-es.service';

describe('Component Tests', () => {
  describe('MDictionaryEs Management Delete Component', () => {
    let comp: MDictionaryEsDeleteDialogComponent;
    let fixture: ComponentFixture<MDictionaryEsDeleteDialogComponent>;
    let service: MDictionaryEsService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryEsDeleteDialogComponent]
      })
        .overrideTemplate(MDictionaryEsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDictionaryEsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDictionaryEsService);
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
