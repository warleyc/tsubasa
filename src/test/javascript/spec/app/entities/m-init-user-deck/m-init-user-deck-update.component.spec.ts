/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MInitUserDeckUpdateComponent } from 'app/entities/m-init-user-deck/m-init-user-deck-update.component';
import { MInitUserDeckService } from 'app/entities/m-init-user-deck/m-init-user-deck.service';
import { MInitUserDeck } from 'app/shared/model/m-init-user-deck.model';

describe('Component Tests', () => {
  describe('MInitUserDeck Management Update Component', () => {
    let comp: MInitUserDeckUpdateComponent;
    let fixture: ComponentFixture<MInitUserDeckUpdateComponent>;
    let service: MInitUserDeckService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MInitUserDeckUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MInitUserDeckUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MInitUserDeckUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MInitUserDeckService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MInitUserDeck(123);
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
        const entity = new MInitUserDeck();
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
