/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDummyOpponentUpdateComponent } from 'app/entities/m-dummy-opponent/m-dummy-opponent-update.component';
import { MDummyOpponentService } from 'app/entities/m-dummy-opponent/m-dummy-opponent.service';
import { MDummyOpponent } from 'app/shared/model/m-dummy-opponent.model';

describe('Component Tests', () => {
  describe('MDummyOpponent Management Update Component', () => {
    let comp: MDummyOpponentUpdateComponent;
    let fixture: ComponentFixture<MDummyOpponentUpdateComponent>;
    let service: MDummyOpponentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDummyOpponentUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MDummyOpponentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MDummyOpponentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDummyOpponentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDummyOpponent(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDummyOpponent();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
