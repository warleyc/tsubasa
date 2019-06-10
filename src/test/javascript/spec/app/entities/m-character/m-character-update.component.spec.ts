/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCharacterUpdateComponent } from 'app/entities/m-character/m-character-update.component';
import { MCharacterService } from 'app/entities/m-character/m-character.service';
import { MCharacter } from 'app/shared/model/m-character.model';

describe('Component Tests', () => {
  describe('MCharacter Management Update Component', () => {
    let comp: MCharacterUpdateComponent;
    let fixture: ComponentFixture<MCharacterUpdateComponent>;
    let service: MCharacterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCharacterUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MCharacterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MCharacterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCharacterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCharacter(123);
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
        const entity = new MCharacter();
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
