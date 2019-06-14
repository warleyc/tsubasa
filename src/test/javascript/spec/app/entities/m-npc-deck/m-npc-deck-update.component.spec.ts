/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MNpcDeckUpdateComponent } from 'app/entities/m-npc-deck/m-npc-deck-update.component';
import { MNpcDeckService } from 'app/entities/m-npc-deck/m-npc-deck.service';
import { MNpcDeck } from 'app/shared/model/m-npc-deck.model';

describe('Component Tests', () => {
  describe('MNpcDeck Management Update Component', () => {
    let comp: MNpcDeckUpdateComponent;
    let fixture: ComponentFixture<MNpcDeckUpdateComponent>;
    let service: MNpcDeckService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MNpcDeckUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MNpcDeckUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MNpcDeckUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MNpcDeckService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MNpcDeck(123);
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
        const entity = new MNpcDeck();
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
