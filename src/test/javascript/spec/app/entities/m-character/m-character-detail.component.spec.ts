/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCharacterDetailComponent } from 'app/entities/m-character/m-character-detail.component';
import { MCharacter } from 'app/shared/model/m-character.model';

describe('Component Tests', () => {
  describe('MCharacter Management Detail Component', () => {
    let comp: MCharacterDetailComponent;
    let fixture: ComponentFixture<MCharacterDetailComponent>;
    const route = ({ data: of({ mCharacter: new MCharacter(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCharacterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MCharacterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCharacterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mCharacter).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
