/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MNpcCardUpdateComponent } from 'app/entities/m-npc-card/m-npc-card-update.component';
import { MNpcCardService } from 'app/entities/m-npc-card/m-npc-card.service';
import { MNpcCard } from 'app/shared/model/m-npc-card.model';

describe('Component Tests', () => {
  describe('MNpcCard Management Update Component', () => {
    let comp: MNpcCardUpdateComponent;
    let fixture: ComponentFixture<MNpcCardUpdateComponent>;
    let service: MNpcCardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MNpcCardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MNpcCardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MNpcCardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MNpcCardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MNpcCard(123);
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
        const entity = new MNpcCard();
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
