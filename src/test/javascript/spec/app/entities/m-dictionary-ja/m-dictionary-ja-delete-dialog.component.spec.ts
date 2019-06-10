/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryJaDeleteDialogComponent } from 'app/entities/m-dictionary-ja/m-dictionary-ja-delete-dialog.component';
import { MDictionaryJaService } from 'app/entities/m-dictionary-ja/m-dictionary-ja.service';

describe('Component Tests', () => {
  describe('MDictionaryJa Management Delete Component', () => {
    let comp: MDictionaryJaDeleteDialogComponent;
    let fixture: ComponentFixture<MDictionaryJaDeleteDialogComponent>;
    let service: MDictionaryJaService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryJaDeleteDialogComponent]
      })
        .overrideTemplate(MDictionaryJaDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDictionaryJaDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDictionaryJaService);
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
